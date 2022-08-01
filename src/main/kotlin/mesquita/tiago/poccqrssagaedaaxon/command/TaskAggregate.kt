package mesquita.tiago.poccqrssagaedaaxon.command

import mesquita.tiago.poccqrssagaedaaxon.core.events.TaskCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.springframework.beans.BeanUtils

@Aggregate
class TaskAggregate() {

    @AggregateIdentifier
    var taskId: String? = null
    var description: String? = null
    
    @CommandHandler
    constructor(createTaskCommand: CreateTaskCommand) : this() {
        // Validate Create Task Command - incluir aqui as validaćões do comando
        // ... ex
        if (createTaskCommand.description.isEmpty()) {
            throw IllegalArgumentException("Description is required")
        }
        
        val taskCreatedEvent = TaskCreatedEvent()
        BeanUtils.copyProperties(createTaskCommand, taskCreatedEvent)
        
        AggregateLifecycle.apply(taskCreatedEvent)
    }
    
    @EventSourcingHandler // persiste o evento no event store
    fun on(taskCreatedEvent: TaskCreatedEvent) {
        this.taskId = taskCreatedEvent.taskId
        this.description = taskCreatedEvent.description
    }
}