package psychegrammer.example.onething.model

class Thing() {
    var thingName: String? = null
    var assignedBy: String? = null
    var assignedTo: String? = null
    var timeAssigned: Long? = null
    var id: Int? = null

    constructor(thing: Thing, assignedBy: String, assignedTo: String, timeAssigned: Long, id: Int): this() {

        this.thingName = thingName
        this.assignedBy = assignedBy
        this.assignedTo = assignedTo
        this.timeAssigned = timeAssigned
        this.id = id

    }
}