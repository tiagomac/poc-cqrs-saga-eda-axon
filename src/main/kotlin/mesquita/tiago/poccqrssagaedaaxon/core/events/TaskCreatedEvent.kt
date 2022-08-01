package mesquita.tiago.poccqrssagaedaaxon.core.events

data class TaskCreatedEvent(
    var taskId: String? = null,
    var description: String? = null
)