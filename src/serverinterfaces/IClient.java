package serverinterfaces;

/**
 *
 * @author Grønborg
 */
public interface IClient {

    public void closeConnection();

    public void sendMessage(String message);

    public String getName();

    public void setName(String name);

}
