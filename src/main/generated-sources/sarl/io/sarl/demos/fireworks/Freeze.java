package io.sarl.demos.fireworks;

import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SuppressWarnings("all")
public class Freeze extends Event {
  public boolean value;
  
  public Freeze(final boolean value) {
    this.value = value;
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
    Freeze other = (Freeze) obj;
    if (other.value != this.value)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (this.value ? 1231 : 1237);
    return result;
  }
  
  /**
   * Returns a String representation of the Freeze event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("value  = ").append(this.value);
    return result.toString();
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 2771119382L;
}
