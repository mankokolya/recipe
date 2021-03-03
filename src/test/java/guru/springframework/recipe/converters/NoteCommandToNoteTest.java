package guru.springframework.recipe.converters;

import guru.springframework.recipe.commands.NoteCommand;
import guru.springframework.recipe.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteCommandToNoteTest {
    private static final Long ID_VALUE = 1L;
    private static final String NOTES = "Notes";

    NoteCommandToNote converter;

    @Before
    public void setUp(){
        converter = new NoteCommandToNote();
    }

    @Test
    public void testNull() {
       assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() {
        assertNotNull(converter.convert(new NoteCommand()));
    }
    @Test
    public void testConvert() {
        NoteCommand command = new NoteCommand();
        command.setId(ID_VALUE);
        command.setRecipeNotes(NOTES);

        Notes notes = converter.convert(command);

        assertEquals(ID_VALUE, notes.getId());
        assertEquals(NOTES, notes.getRecipeNotes());
    }



}
