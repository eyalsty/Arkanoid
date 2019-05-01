package interfaces;

/**
 * Menu<T) type interface. allows to have few choosing options, and they all generics
 * @param <T> - generic return value
 */
public interface Menu<T> extends Animation {

    /**.
     * adds another selection option to the menu
     * @param key - key to wait for
     * @param message - line to print
     * @param returnVal - what to return
     */
    void addSelection(String key, String message, T returnVal);

    /**.
     *
     * @return the status member of the menu (generic)
     */
    T getStatus();

    /**.
     * resets the Status member and isStop members to null (and false)
     */
    void clearStatus();

    /**.
     * adds subMenu to the current menu
     * @param key - key to wait for
     * @param message - line to print
     * @param subMenu - the sub menu (Menu type)
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
