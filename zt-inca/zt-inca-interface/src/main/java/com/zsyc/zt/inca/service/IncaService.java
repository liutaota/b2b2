package com.zsyc.zt.inca.service;

import java.time.LocalDateTime;

/**
 * 所有英克系统的业务操作
 */
public interface IncaService  {

    Boolean checkBusinessOperationIsPermit(LocalDateTime dateTime, Integer entryId);
}
