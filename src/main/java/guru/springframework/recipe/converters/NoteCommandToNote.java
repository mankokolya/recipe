package guru.springframework.recipe.converters;

import guru.springframework.recipe.commands.NoteCommand;
import guru.springframework.recipe.domain.Notes;
import org.springframework.core.convert.converter.Converter;


public class NoteCommandToNote implements Converter<NoteCommand, Notes> {
    @Override
    public Notes convert(NoteCommand noteCommand) {
        if (noteCommand == null) {
            return null;
        }
        Notes notes = new Notes();
        notes.setId(noteCommand.getId());
        notes.setRecipeNotes(noteCommand.getRecipeNotes());
        return notes;
    }
}