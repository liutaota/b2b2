package com.zsyc.zt.inca.service;

import com.zsyc.zt.inca.entity.CustomTransVo;

public interface TransControlService {

    CustomTransVo get(Long customId,Long transDocId);

    CustomTransVo getByTransDtlId(Long transDtlId);

    CustomTransVo getByTransDocId(Long customId, Long transDocId);

    CustomTransVo getByCustomId(Long customId);
}
