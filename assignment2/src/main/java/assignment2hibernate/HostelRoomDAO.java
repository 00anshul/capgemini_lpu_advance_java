package assignment2hibernate;

public interface HostelRoomDAO {

    void saveRoom(HostelRoom room);

    HostelRoom findRoom(int id);
}
