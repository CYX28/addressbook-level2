package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, Clementi Ave 3, #12-34, 231534";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses must be in the format a/BLOCK, " +
                                                                "STREET, UNIT, POSTAL_CODE";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    public final String value;
    public final Block block;
    public final Street street;
    public final Unit unit;
    public final PostalCode postalCode;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        this.isPrivate = isPrivate;

        String[] addressAttributes = splitAddressByComma(trimmedAddress);

        if (!isValidAddress(trimmedAddress) || addressAttributes.length != 4) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }

        // Instantiate address attributes
        this.block = new Block(addressAttributes[0]);
        this.street = new Street(addressAttributes[1]);
        this.unit = new Unit(addressAttributes[2]);
        this.postalCode = new PostalCode(addressAttributes[3]);

        this.value = formatAddressString(block, street, unit, postalCode);
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String[] splitAddressByComma(String toSplit) {
        return toSplit.trim().split(",");
    }

    public String formatAddressString(Block block, Street street, Unit unit, PostalCode postalCode) {
        return block + ", " + street + ", " + unit + ", " + postalCode;
    }
}
