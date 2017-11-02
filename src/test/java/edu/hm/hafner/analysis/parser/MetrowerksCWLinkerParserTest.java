package edu.hm.hafner.analysis.parser;

import java.io.IOException;
import java.util.Iterator;

import org.junit.Test;

import edu.hm.hafner.analysis.Issue;
import edu.hm.hafner.analysis.Issues;
import edu.hm.hafner.analysis.Priority;
import static org.junit.Assert.*;

/**
 * Tests the class {@link MetrowerksCWLinkerParser}.
 */
public class MetrowerksCWLinkerParserTest extends ParserTester {
    private static final String INFO_CATEGORY = "Info";
    private static final String WARNING_CATEGORY = "Warning";
    private static final String ERROR_CATEGORY = "ERROR";
    private static final String TYPE = new MetrowerksCWLinkerParser().getId();

    /**
     * Parses a file with two GCC warnings.
     *
     * @throws IOException if the file could not be read
     */
    @Test
    public void testWarningsParser() throws IOException {
        Issues warnings = new MetrowerksCWLinkerParser().parse(openFile());

        assertEquals(3, warnings.size());

        Iterator<Issue> iterator = warnings.iterator();
        Issue annotation = iterator.next();
        checkWarning(annotation,
                0,
                "L1822: Symbol TestFunction in file e:/work/PATH/PATH/PATH/PATH/appl_src.lib is undefined",
                "See Warning message",
                TYPE, ERROR_CATEGORY, Priority.HIGH);
        annotation = iterator.next();
        checkWarning(annotation,
                0,
                "L1916: Section name TEST_SECTION is too long. Name is cut to 90 characters length",
                "See Warning message",
                TYPE, WARNING_CATEGORY, Priority.NORMAL);
        annotation = iterator.next();
        checkWarning(annotation,
                0,
                "L2: Message overflow, skipping WARNING messages",
                "See Warning message",
                TYPE, INFO_CATEGORY, Priority.LOW);
    }


    /** {@inheritDoc} */
    @Override
    protected String getWarningsFile() {
        return "MetrowerksCWLinker.txt";
    }
}

