package org.yaezakura.ljq.stuff;

import java.util.List;

public class Employer {
	String name;
	String idCard;
	String detailUrl;
	List<EngineerType> eList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getDetailUrl() {
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	public List<EngineerType> geteList() {
		return eList;
	}
	public void seteList(List<EngineerType> eList) {
		this.eList = eList;
	}

}

class EngineerType{
	String zhuCheLeiBie;
	String zhuCheZhuanYe;
	String zhengShuBianHao;
	String zhiYeYinZhangHao;
	String youXiaoQi;
	String zhuCheDanWei;
	public String getZhuCheLeiBie() {
		return zhuCheLeiBie;
	}
	public void setZhuCheLeiBie(String zhuCheLeiBie) {
		this.zhuCheLeiBie = zhuCheLeiBie;
	}
	public String getZhuCheZhuanYe() {
		return zhuCheZhuanYe;
	}
	public void setZhuCheZhuanYe(String zhuCheZhuanYe) {
		this.zhuCheZhuanYe = zhuCheZhuanYe;
	}
	public String getZhengShuBianHao() {
		return zhengShuBianHao;
	}
	public void setZhengShuBianHao(String zhengShuBianHao) {
		this.zhengShuBianHao = zhengShuBianHao;
	}
	public String getZhiYeYinZhangHao() {
		return zhiYeYinZhangHao;
	}
	public void setZhiYeYinZhangHao(String zhiYeYinZhangHao) {
		this.zhiYeYinZhangHao = zhiYeYinZhangHao;
	}
	public String getYouXiaoQi() {
		return youXiaoQi;
	}
	public void setYouXiaoQi(String youXiaoQi) {
		this.youXiaoQi = youXiaoQi;
	}
	public String getZhuCheDanWei() {
		return zhuCheDanWei;
	}
	public void setZhuCheDanWei(String zhuCheDanWei) {
		this.zhuCheDanWei = zhuCheDanWei;
	}
}
