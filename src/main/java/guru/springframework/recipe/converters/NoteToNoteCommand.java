package guru.springframework.recipe.converters;

import com.sun.istack.Nullable;
import guru.springframework.recipe.commands.NoteCommand;
import guru.springframework.recipe.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NoteToNoteCommand implements Converter<Notes, NoteCommand> {
    @Override
    @Nullable
    @Synchronized
    public NoteCommand convert(Notes notes) {
        if (notes == null) {
            return null;
        }

        NoteCommand command = new NoteCommand();
        command.setId(notes.getId());
        command.setRecipeNotes(notes.getRecipeNotes());
        return command;
    }
}
