data class DomainEvent(val id: String, val type: String, val payload: String?)

interface EventProducer {
//  fun produce(event: DomainEvent)
}
interface EventConsumer {
  fun consume(event: DomainEvent)
}

interface EventBus {
  fun publish(event: DomainEvent)
  fun addChannel(channel: EventChannel)
}

interface EventChannel {
  val name: String
  fun add(event: DomainEvent)
  fun addConsumer(consumer: EventConsumer)
}

class EventBusImpl : EventBus {
  private val channels = mutableListOf<EventChannel>()

  override fun publish(event: DomainEvent) {
    channels.forEach { it.add(event) }
  }

  override fun addChannel(channel: EventChannel) {
    channels.add(channel)
    println("Channel [${channel.name.uppercase()}] is added to EventBus")
  }
}

class EventChannelImpl(override val name: String) : EventChannel {
  private val consumers = mutableListOf<EventConsumer>()

  override fun add(event: DomainEvent) {
    consumers.forEach { it.consume(event) }
  }

  override fun addConsumer(consumer: EventConsumer) {
    consumers.add(consumer)
  }
}

class CarSensor(private val eventBus: EventBus) : EventProducer {
  fun detectEnteringCar(carId: String, payload: String?) {
    val event = DomainEvent(carId, "entering", payload)
    eventBus.publish(event)
  }

  fun detectExitingCar(vehicleId: String, payload: String?) {
    val event = DomainEvent(vehicleId, "exiting", payload)
    eventBus.publish(event)
  }
}

class TrafficLight : EventConsumer {
  override fun consume(event: DomainEvent) {
    if (event.type == "entering") {
      println("Vehicle ${event.id} is entering. Adjusting traffic light timings.")
    } else if (event.type == "exiting") {
      println("Vehicle ${event.id} has left. Resetting traffic light timing.")
    }
  }
}

class FlightControlTower(private val eventBus: EventBus) : EventProducer {
  fun detectFlightLanding(flightNumber: String, payload: String?) {
    val event = DomainEvent(flightNumber, "landing", payload)
    eventBus.publish(event)
  }

  fun detectFlightTakeoff(flightNumber: String, payload: String?) {
    val event = DomainEvent(flightNumber, "takeoff", payload)
    eventBus.publish(event)
  }
}

class AirTrafficControl(private val id: String) : EventConsumer {
  override fun consume(event: DomainEvent) {
    if (event.type == "landing") {
      println("[AirTrafficControl:$id] Flight ${event.id} is landing. Clearing runway.")
    } else if (event.type == "takeoff") {
      println("[AirTrafficControl:$id] Flight ${event.id} is taking off. Monitoring airspace.")
    }
  }
}

/* ==================== */
data class Car(val registrationId: String, val color: String)
data class Flight(val flightNumber: String, val airline: String)

fun main() {
  val eventBus = EventBusImpl()
  val carEventChannel = EventChannelImpl("car")
  val flightEventChannel = EventChannelImpl("flight")
  eventBus.addChannel(carEventChannel)
  eventBus.addChannel(flightEventChannel)

  val carSensor = CarSensor(eventBus)
  val trafficLight = TrafficLight()
  carEventChannel.addConsumer(trafficLight)

  val car1 = Car("กขค 99999", "red")
  carSensor.detectEnteringCar(car1.registrationId, car1.toString())
  carSensor.detectExitingCar(car1.registrationId, car1.toString())

  val flightControlTower = FlightControlTower(eventBus)
  val airTrafficControl1 = AirTrafficControl("ACT-111")
  val airTrafficControl2 = AirTrafficControl("ACT-999")
  flightEventChannel.addConsumer(airTrafficControl1)
  flightEventChannel.addConsumer(airTrafficControl2)

  val flight1 = Flight("TG123", "Thai Airways")
  val flight2 = Flight("VJ456", "VietJet Air")
  flightControlTower.detectFlightLanding(flight2.flightNumber, flight2.toString())
  flightControlTower.detectFlightTakeoff(flight1.flightNumber, flight1.toString())
}
