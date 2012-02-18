package spout;

public interface Material {

    public short getId();

    public short getData();

    public boolean hasSubtypes();

    public String getName();

    public String getDisplayName();

    public void setDisplayName(String name);
}
