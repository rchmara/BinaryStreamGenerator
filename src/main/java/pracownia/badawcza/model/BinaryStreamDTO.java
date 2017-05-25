package pracownia.badawcza.model;

/**
 * Created by tomas on 21.05.2017.
 */
public class BinaryStreamDTO {
    private int id;
    private final String name;
    private final int d;
    private final int l;
    private final int r;
    private final String stream;

    public BinaryStreamDTO(String name, int d, int l, int r, String stream) {
        this.name = name;
        this.d = d;
        this.l = l;
        this.r = r;
        this.stream = stream;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getD() {
        return d;
    }

    public int getL() {
        return l;
    }

    public int getR() {
        return r;
    }

    public String getStream() {
        return stream;
    }

    public void setId(int id) {
        this.id = id;
    }
}
