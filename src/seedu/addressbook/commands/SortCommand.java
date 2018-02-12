package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Sorts and lists all persons in the address book to the user.
 */
public class SortCommand extends Command {
    
    public static final String COMMAND_WORD = "sort";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts and displays all persons in the address book as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        // Sort Persons by name
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView().stream()
                .sorted(((o1, o2) -> o1.getName().toString().compareToIgnoreCase(o2.getName().toString())))
                .collect(Collectors.toList());
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
    
}
