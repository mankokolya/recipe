package guru.springframework.recipe.converters;

import guru.springframework.recipe.commands.NoteCommand;
import guru.springframework.recipe.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteToNoteCommandTest {
    private static final Long ID_VALUE = 1L;
    private static final String NOTES = "notes";

    NoteToNoteCommand converter;

    @Before
    public void setUp(){
        converter = new NoteToNoteCommand();
    }

    @Test
    public void testNull(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void testConvert() {
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(NOTES);

        NoteCommand command = converter.convert(notes);

        assertEquals(ID_VALUE, command.getId());
        assertEquals(NOTES, command.getRecipeNotes());
    }


}
