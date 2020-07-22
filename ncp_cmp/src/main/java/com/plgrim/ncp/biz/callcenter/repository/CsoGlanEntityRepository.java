package com.plgrim.ncp.biz.callcenter.repository;

import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnslt;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetail;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.repository.cso.CsoCnsltRepository;
import com.plgrim.ncp.base.repository.cso.CsoGlanRepository;
import com.plgrim.ncp.biz.callcenter.data.CounselSearchDTO;
import com.plgrim.ncp.biz.callcenter.data.CsGlanSearchDTO;
import com.plgrim.ncp.biz.callcenter.result.CounselResult;
import com.plgrim.ncp.biz.callcenter.result.DetailCounselResult;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CsoGlanEntityRepository extends CsoGlanRepository {


}