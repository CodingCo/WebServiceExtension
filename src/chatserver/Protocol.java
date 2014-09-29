package chatserver;

/**
 *
 * @author simon
 */
public class Protocol {

    // Default protocol
    public static final String CONNECT = "CONNECT#";
    public static final String CLOSE = "CLOSE#";
    public static final String SEND = "SEND#";
    public static final String ONLINE = "ONLINE#";
    public static final String MESSAGE = "MESSAGE#";
    public static final String ALL = "*#";

    // default server response
    public static final String SERVER_ONLINE_RESPONSE = MESSAGE + "server#" + "you are already online";
    public static final String SERVER_NO_SUCH_USER = MESSAGE + "server#" + "could not find user: ";

    static class CheckMessage {

        static int registrerProtocolType(String message) {
            if (message.startsWith(CONNECT)) {
                return 1;
            }
            if (message.startsWith(SEND)) {
                return 2;
            }
            if (message.startsWith(CLOSE)) {
                return 3;
            }
            return 0;
        }
    }
}
