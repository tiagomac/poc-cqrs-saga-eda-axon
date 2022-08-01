package mesquita.tiago.poccqrssagaedaaxon.domain.task

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.NotEmpty

data class Task(
    @field:NotEmpty(message = "Task must have a description")
    val description: String
)