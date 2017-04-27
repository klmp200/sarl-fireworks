package io.sarl.demos.fireworks;

import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SuppressWarnings("all")
public class SetupSettings extends Event {
  public Integer rocketsQuantity;
  
  public Integer fireQuatity;
  
  public Double gravity;
  
  public Double maxWidth;
  
  public SetupSettings(final Integer rq, final Integer fq, final Double grav, final Double max) {
    this.rocketsQuantity = rq;
    this.fireQuatity = fq;
    this.gravity = grav;
    this.maxWidth = max;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SetupSettings other = (SetupSettings) obj;
    if (this.rocketsQuantity == null) {
      if (other.rocketsQuantity != null)
        return false;
    } else if (!this.rocketsQuantity.equals(other.rocketsQuantity))
      return false;
    if (this.fireQuatity == null) {
      if (other.fireQuatity != null)
        return false;
    } else if (!this.fireQuatity.equals(other.fireQuatity))
      return false;
    if (this.gravity == null) {
      if (other.gravity != null)
        return false;
    } else if (!this.gravity.equals(other.gravity))
      return false;
    if (this.maxWidth == null) {
      if (other.maxWidth != null)
        return false;
    } else if (!this.maxWidth.equals(other.maxWidth))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.rocketsQuantity== null) ? 0 : this.rocketsQuantity.hashCode());
    result = prime * result + ((this.fireQuatity== null) ? 0 : this.fireQuatity.hashCode());
    result = prime * result + ((this.gravity== null) ? 0 : this.gravity.hashCode());
    result = prime * result + ((this.maxWidth== null) ? 0 : this.maxWidth.hashCode());
    return result;
  }
  
  /**
   * Returns a String representation of the SetupSettings event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("rocketsQuantity  = ").append(this.rocketsQuantity);
    result.append("fireQuatity  = ").append(this.fireQuatity);
    result.append("gravity  = ").append(this.gravity);
    result.append("maxWidth  = ").append(this.maxWidth);
    return result.toString();
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 354887804L;
}
