package com.plgrim.ncp.biz.order.service;

import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.enums.OrderClaimEnum;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;

@Service
public class OrderPhoneService extends OrderBoAbstractService {

	public void insertOrder(OrderBoDTO orderDTO) throws Exception {

		creatOrder(orderDTO);
	}

	@Override
	void setOrder(Ord ord) {

		ord.setOrdStatCd(OrderClaimEnum.ORD_STAT_PAY_WAIT.toString());
//		ord.setOrdTpCd(OrderClaimEnum.ORD_TP_CD_TEL_ORD.toString());

	}

	@Override
	boolean mbrChecK() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	boolean pointYn() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	boolean invManageYn() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	boolean godChecK() {
		return true;
	}
	@Override
	void invProcessor(OrdGodExtend ordGodEntity) throws Exception {
		// TODO Auto-generated method stub

	}

    @Override
    void pckageshapeChk(OrdGodExtend ordGodEntity) throws Exception {
        // TODO Auto-generated method stub
        
    }
}
