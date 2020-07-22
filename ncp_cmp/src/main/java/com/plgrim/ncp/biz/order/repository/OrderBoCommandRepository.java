package com.plgrim.ncp.biz.order.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodCpstGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInvExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfAffOrd;
import com.plgrim.ncp.base.entities.datasource1.inf.InfTmprAffOrd;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdCpstGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrmExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;

@Repository
public class OrderBoCommandRepository extends AbstractRepository {


	/** 대량주문 확정 여부 */
	public int updatelagQtyOrdDcsn(Ord ord) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.order.updatelagQtyOrdDcsn", ord);
	}

	public int updateOptionChange(OrdGodExtend ordGodExtend) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.order.updateOptionChange", ordGodExtend);
	}

	/** 입금확정완료 */
	public int confirmDeposit(Ord ord) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.order.confirmDeposit", ord);
	}

	public int virtlDlvCompt(Ord ord) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.order.virtlDlvCompt", ord);
	}
	
	public int virtlDlvCompt4PayWait(Ord ord) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.order.virtlDlvCompt4PayWait", ord);
	}

	public void insertInfAffOrdExcel(InfAffOrd infAffOrd) throws Exception {

		getSession1().insert("com.plgrim.ncp.biz.order.insertInfAffOrdExcel", infAffOrd);

	}
/*
	public List<InfTmprAffOrdExtend> selectErrExcelAffList() throws Exception {

		return getSession1().selectList("com.plgrim.ncp.biz.order.selectErrExcelAffList");

	}
*/
   public List<InfAffOrd> selectAffAditFeeRt(InfAffOrd infAffOrd) throws Exception {

        return getSession1().selectList("com.plgrim.ncp.biz.order.selectAffAditFeeRt",infAffOrd);

    }
   public List<InfAffOrd> selectAffCoOrdList(OrderBoDTO orderBoDTO) throws Exception {

       return getSession1().selectList("com.plgrim.ncp.biz.order.selectAffCoOrdList",orderBoDTO);

   }
	
	public List<OrdExtend> selectAffOrd(OrderBoDTO orderBoDTO) throws Exception {

		return getSession1().selectList("com.plgrim.ncp.biz.order.selectAffOrd", orderBoDTO);

	}
	
   public List<GodCpstGodCnnc> selectAffPckageshape(OrderBoDTO orderBoDTO) throws Exception {

        return getSession1().selectList("com.plgrim.ncp.biz.order.selectAffPckageshape", orderBoDTO);

    }
   
	public void insertInfTmprAffOrdTemp(List<InfTmprAffOrd> map) throws Exception {

		getSession1().insert("com.plgrim.ncp.biz.order.insertInfTmprAffOrdTemp", map);

	}

	public void updateAffOrd(InfAffOrd infAffOrd) throws Exception {

		getSession1().insert("com.plgrim.ncp.biz.order.updateAffOrd", infAffOrd);

	}
	
   public void updateAffOrdRt(InfAffOrd infAffOrd) throws Exception {

        getSession1().insert("com.plgrim.ncp.biz.order.updateAffOrdRt", infAffOrd);

    }

	public void updateGoodsShopItmInvOrd(GodShopItmInvExtend godShopItmInvExtend) throws Exception {

		getSession1().insert("com.plgrim.ncp.biz.order.updateGoodsShopItmInvOrd", godShopItmInvExtend);

	}

	public void updateGoodsItmOrd(GodItm godItm) throws Exception {

		getSession1().insert("com.plgrim.ncp.biz.order.updateGoodsItmOrd", godItm);

	}

	public void deleteAffOrd(InfAffOrd infAffOrd) throws Exception {

		getSession1().update("com.plgrim.ncp.biz.order.deleteAffOrd", infAffOrd);

	}

	public void updateTmprAffOrdTemp() throws Exception {

		getSession1().insert("com.plgrim.ncp.biz.order.updateTmprAffOrdTemp");

	}

	/** 주문상태 수정 */
	public int updateOrdStatCd(Ord ord) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.order.updateOrdStatCd", ord);
	}

	/** 주문상태 배송완료 수정 */
	public int updateOrdStatAboutCompt(Ord ord) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.order.updateOrdStatAboutCompt", ord);
	}
	
   public int pkUpdateLgsDlvsp(LgsDlvsp lgsDlvsp) throws Exception {
        return getSession1().update("com.plgrim.ncp.biz.order.pkUpdateLgsDlvsp", lgsDlvsp);
    }
	    
	/**
	 * 주문 - 교환접수 시 주문상품 등록
	 * @param ordGodExtend
	 * @throws Exception
	 */
	public void insertOrdGodForClm(OrdGodExtend ordGodExtend) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.insertOrdGodForClm", ordGodExtend);
	}
	
	
	/**
	 * 주문 - 교환접수 시 주문상품적용프로모션 등록
	 * @param ordGodExtend
	 * @throws Exception
	 */
	public void insertOrdGodAplPrmForClm(OrdGodAplPrmExtend ordGodAplPrmExtend) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.insertOrdGodAplPrmForClm", ordGodAplPrmExtend);
	}
	
		
	/**
	 * 주문 - 교환접수 시 구성상품연결 등록
	 * @param ordCpstGodCnnc
	 * @throws Exception
	 */
	public void insertOrdCpstGodCnncForClm(OrdCpstGodCnnc ordCpstGodCnnc) throws Exception {
		getSession1().insert("com.plgrim.ncp.base.insertOrdCpstGodCnnc", ordCpstGodCnnc);		
	}
	
	
	
	public List<OrdGodAplPrmExtend> selectOrdGodAplPrmForClmList(OrdGodAplPrmExtend ordGodAplPrmExtend) throws Exception {

		return getSession1().selectList("com.plgrim.ncp.biz.order.selectOrdGodAplPrmForClmList", ordGodAplPrmExtend);

	}

	/**
	 * 주문 상품 등록.
	 * 1. 수정일자   : 2016-02-26
	 * 2. 수정자     : 김재성 (jskim27)
	 * 3. 요청 SR NO : #17045
	 * 4. 수정내용   : [제휴] '주문상품' 데이터 생성 시 '입점업체수수료율' 등록
	 *					- ORD_GOD 데이터 생성 시, GOD 테이블에서 조회해서 INSERT
	 * @param ordGod the OrdGod
	 * @throws SQLException the SQL exception
	 */
	public void insertOrdGod(OrdGod ordGod) throws Exception {
		this.getSession1().insert("com.plgrim.ncp.biz.order.insertOrdGod", ordGod);
	}
	
	/**
	 * 세트상품 교환접수시 세트대표상품 등록
	 * @param ordGodExtend
	 * @throws Exception
	 */
	public void insertSetExchgGodForClm(OrdGodExtend ordGodExtend) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.order.insertSetExchgGodForClm", ordGodExtend);
	}
	
	/**
	 * 교환접수 시 구성상품연결 등록
	 * @param ordCpstGodCnnc
	 * @throws Exception
	 */
	public void insertSetExchgCpstGodCnnc(OrdCpstGodCnnc ordCpstGodCnnc) throws Exception {
		getSession1().insert("com.plgrim.ncp.base.insertSetExchgCpstGodCnnc", ordCpstGodCnnc);		
	}
}
