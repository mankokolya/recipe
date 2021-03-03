package guru.springframework.recipe.converters;

import guru.springframework.recipe.commands.NoteCommand;
import guru.springframework.recipe.domain.Notes;
import org.springframework.core.convert.converter.Converter;

public class NoteToNoteCommand implements Converter<Notes, NoteCommand> {
    @Override
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
