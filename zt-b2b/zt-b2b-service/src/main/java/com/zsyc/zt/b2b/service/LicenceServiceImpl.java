package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Licence;
import com.zsyc.zt.b2b.entity.Member;
import com.zsyc.zt.b2b.mapper.LicenceMapper;
import com.zsyc.zt.b2b.mapper.MemberMapper;
import com.zsyc.zt.b2b.vo.LicenceVo;
import com.zsyc.zt.b2b.vo.MemberLicenceVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
public class LicenceServiceImpl implements LicenceService {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private LicenceMapper licenceMapper;

    @Override
    public void updateLicenceStatus() {
        /**
         * 1.获取平台可用客户
         * 2.获取对应客户证照信息
         * 3.判断有效期修改证照使用状态：在当前时间1个月前为即将过期，大于当前时间1个月后为正常，小于当前时间为过期
         * 4.每天0点执行一次
         */
       /* List<Member> memberList = this.memberMapper.selectList(new QueryWrapper<Member>().gt("ERP_USER_ID",0));
        for (Member member : memberList){
            List<MemberLicenceVo> memberLicenceVoList = this.memberMapper.getMemberLicenceList(member.getErpUserId());
            for (MemberLicenceVo memberLicenceVo : memberLicenceVoList){
                Licence licence = new Licence();
                if (memberLicenceVo.getValidEndTime().isBefore(LocalDateTime.now().plusMonths(1L))){
                    licence.setStatus(Licence.EStatus.BE_ABOUT_TO_EXPIRES.val());
                }else if (memberLicenceVo.getValidEndTime().isAfter(LocalDateTime.now().plusMonths(1L))){
                    licence.setStatus(Licence.EStatus.NORMAL.val());
                }else if (memberLicenceVo.getValidEndTime().isBefore(LocalDateTime.now())){
                    licence.setStatus(Licence.EStatus.EXPIRES.val());
                }
            }
        }*/

        /**
         * 已经过期的证照
         */
        this.licenceMapper.updateLicenceDoneValid();

        /**
         * 即将过期的证照
         */
        this.licenceMapper.updateLicenceTOValid();

    }

}
