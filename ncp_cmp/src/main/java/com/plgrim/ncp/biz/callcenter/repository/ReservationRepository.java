package com.plgrim.ncp.biz.callcenter.repository;

import java.util.List;

import com.plgrim.ncp.framework.page.PageParam;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.biz.callcenter.data.ReservationSearchDTO;
import com.plgrim.ncp.biz.callcenter.result.ReservationResult;
import com.plgrim.ncp.framework.data.SystemPK;


@Repository
public class ReservationRepository extends AbstractRepository{


	public List<ReservationResult> getListReservation(ReservationSearchDTO reservationSearchDTO , PageParam pageParam) throws Exception{
		
		List<ReservationResult> list = getSession1().selectList("com.plgrim.ncp.biz.callcenter.reservation.getListReservation", reservationSearchDTO);
		return list;
	}


	public long getListReservationTotal(ReservationSearchDTO reservationSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.reservation.getListReservationTotal", reservationSearchDTO);
	}
}