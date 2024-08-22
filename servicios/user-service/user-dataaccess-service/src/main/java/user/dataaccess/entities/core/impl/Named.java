package user.dataaccess.entities.core.impl;

import java.io.Serializable;

public interface Named extends Serializable {

  String getNombre();

  void setNombre(String nombre);

  String getDescripcion();

  void setDescripcion(String descripcion);

}
