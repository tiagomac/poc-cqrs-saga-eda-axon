package mesquita.tiago.poccqrssagaedaaxon.command

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateTaskCommand(
    @TargetAggregateIdentifier
    val taskId: String,
    val description: String
)