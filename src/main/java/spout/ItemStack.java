package spout;

import java.util.Map;
import org.spout.nbt.Tag;

/**
 * Represents a stack of items
 */
public class ItemStack {

    private Material material;
    private int amount;
    private short damage;
    private Map<String, Tag> auxData;

    /**
     * Creates a new ItemStack from the specified Material of the specified
     * amount
     *
     */
    public ItemStack(Material material, int amount) {
        this(material, amount, (short) 0);
    }

    public ItemStack(Material material, int amount, short damage) {
        this.material = material;
        this.amount = amount;
        this.damage = damage;
    }

    /**
     * Gets the Material of the stack
     *
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the Material for the stack
     *
     * @param material the material
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Gets the amount of the Material contained in the item stack
     *
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets amount of the Material contained in the item stack
     *
     * @param amount the amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Gets the damage value for the item
     *
     * @return the damage
     */
    public short getDamage() {
        return damage;
    }

    /**
     * Sets the damage for the item stack
     *
     * @param damage the damage
     */
    public void setDamage(short damage) {
        this.damage = damage;
    }

    /**
     * returns a copy of the map containing the aux data for this stack
     *
     * @return the aux data
     */
    public Map<String, Tag> getAuxData() {
        return Tag.cloneMap(auxData);
    }

    /**
     * Sets the aux data for this stack
     *
     * @return the item stack
     */
    public ItemStack setAuxData(Map<String, Tag> auxData) {
        if (auxData == null) {
            this.auxData = null;
        } else {
            this.auxData = Tag.cloneMap(auxData);
        }
        return this;
    }

    @Override
    public ItemStack clone() {
        ItemStack newStack = new ItemStack(material, amount, damage);
        newStack.setAuxData(auxData);
        return newStack;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ItemStack)) {
            return false;
        }
        ItemStack stack = (ItemStack) other;
        return material.equals(stack.material) && amount == stack.amount && damage == stack.damage && potentialNullEquals(auxData, stack.auxData);
    }

    public static boolean potentialNullEquals(Object a, Object b) {
        if (a == null && b == null) {
            return true;
        } else if (a == null || b == null) {
            return false;
        } else {
            return a.equals(b);
        }
    }

    @Override
    public String toString() {
        return "ItemStack{" + "material=" + material + ",id=" + material.getId() + ",amount=" + amount + ",damage=" + damage + ",auxData=" + auxData + '}';
    }
}
