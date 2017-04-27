package io.sarl.demos.fireworks;

import io.sarl.demos.fireworks.FirePos;
import io.sarl.demos.fireworks.RocketsPos;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import javafx.scene.paint.Color;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SuppressWarnings("all")
public class Positions {
  private HashMap<UUID, RocketsPos> rockets = new HashMap<UUID, RocketsPos>();
  
  private HashMap<UUID, FirePos> fire = new HashMap<UUID, FirePos>();
  
  @Pure
  public Map<UUID, RocketsPos> getRockets() {
    return Collections.<UUID, RocketsPos>unmodifiableMap(this.rockets);
  }
  
  @Pure
  public Map<UUID, FirePos> getFire() {
    return Collections.<UUID, FirePos>unmodifiableMap(this.fire);
  }
  
  public RocketsPos setRocketPosition(final UUID id, final Vector<Double> position) {
    RocketsPos _xblockexpression = null;
    {
      RocketsPos pos = new RocketsPos();
      pos.setPosition(position);
      RocketsPos _xifexpression = null;
      boolean _containsKey = this.rockets.containsKey(id);
      if (_containsKey) {
        RocketsPos _xblockexpression_1 = null;
        {
          pos.setColor(this.rockets.get(id).getColor());
          _xblockexpression_1 = this.rockets.replace(id, pos);
        }
        _xifexpression = _xblockexpression_1;
      } else {
        RocketsPos _xblockexpression_2 = null;
        {
          pos.setColor(Color.color(Math.random(), Math.random(), Math.random()));
          _xblockexpression_2 = this.rockets.put(id, pos);
        }
        _xifexpression = _xblockexpression_2;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public RocketsPos removeRocketPosition(final UUID id) {
    return this.rockets.remove(id);
  }
  
  public boolean hideHocketPosition(final UUID id) {
    return this.rockets.get(id).setHidden(true);
  }
  
  public FirePos setFirePosition(final UUID id, final UUID rocketID, final List<Vector<Double>> positions) {
    FirePos _xblockexpression = null;
    {
      FirePos pos = new FirePos();
      pos.setPositions(positions);
      FirePos _xifexpression = null;
      boolean _containsKey = this.fire.containsKey(id);
      if (_containsKey) {
        FirePos _xblockexpression_1 = null;
        {
          pos.setColor(this.fire.get(id).getColor());
          _xblockexpression_1 = this.fire.replace(id, pos);
        }
        _xifexpression = _xblockexpression_1;
      } else {
        FirePos _xblockexpression_2 = null;
        {
          boolean _containsKey_1 = this.rockets.containsKey(rocketID);
          if (_containsKey_1) {
            pos.setColor(this.rockets.get(rocketID).getColor());
          } else {
            pos.setColor(Color.color(Math.random(), Math.random(), Math.random()));
          }
          _xblockexpression_2 = this.fire.put(id, pos);
        }
        _xifexpression = _xblockexpression_2;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public FirePos removeFirePosition(final UUID id) {
    return this.fire.remove(id);
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
    Positions other = (Positions) obj;
    if (this.rockets == null) {
      if (other.rockets != null)
        return false;
    } else if (!this.rockets.equals(other.rockets))
      return false;
    if (this.fire == null) {
      if (other.fire != null)
        return false;
    } else if (!this.fire.equals(other.fire))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.rockets== null) ? 0 : this.rockets.hashCode());
    result = prime * result + ((this.fire== null) ? 0 : this.fire.hashCode());
    return result;
  }
  
  @SyntheticMember
  public Positions() {
    super();
  }
}
