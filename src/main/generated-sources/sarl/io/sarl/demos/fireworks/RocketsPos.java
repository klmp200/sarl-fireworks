package io.sarl.demos.fireworks;

import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Vector;
import javafx.scene.paint.Color;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SuppressWarnings("all")
public class RocketsPos {
  public final int ROCKETREFRESHDELAY = 100;
  
  private Vector<Double> position = new Vector<Double>();
  
  private Color color;
  
  private boolean hidden = false;
  
  @Pure
  public Vector<Double> getPosition() {
    return this.position;
  }
  
  public Vector<Double> setPosition(final Vector<Double> position) {
    return this.position = position;
  }
  
  @Pure
  public Color getColor() {
    return this.color;
  }
  
  public Color setColor(final Color color) {
    return this.color = color;
  }
  
  public boolean setHidden(final boolean hidden) {
    return this.hidden = hidden;
  }
  
  @Pure
  public boolean getHidden() {
    return this.hidden;
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
    RocketsPos other = (RocketsPos) obj;
    if (other.ROCKETREFRESHDELAY != this.ROCKETREFRESHDELAY)
      return false;
    if (this.position == null) {
      if (other.position != null)
        return false;
    } else if (!this.position.equals(other.position))
      return false;
    if (this.color == null) {
      if (other.color != null)
        return false;
    } else if (!this.color.equals(other.color))
      return false;
    if (other.hidden != this.hidden)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + this.ROCKETREFRESHDELAY;
    result = prime * result + ((this.position== null) ? 0 : this.position.hashCode());
    result = prime * result + ((this.color== null) ? 0 : this.color.hashCode());
    result = prime * result + (this.hidden ? 1231 : 1237);
    return result;
  }
  
  @SyntheticMember
  public RocketsPos() {
    super();
  }
}
