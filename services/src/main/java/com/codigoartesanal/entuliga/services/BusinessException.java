package com.codigoartesanal.entuliga.services;

import org.springframework.core.NestedCheckedException;

public class BusinessException extends NestedCheckedException {

  private static final long serialVersionUID = 1L;

  public BusinessException(String msg) {
    super(msg);
  }

}
