package com.ck.springboot.service.impl;

import com.ck.springboot.mapper.PrescriptionHistoryMapper;
import com.ck.springboot.pojo.*;
import com.ck.springboot.pojo.report.PrescriptionHistoryReportDetail;
import com.ck.springboot.pojo.report.reportSummary;
import com.ck.springboot.service.PrescriptionHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class PrescriptionHistoryServicelmpl implements PrescriptionHistoryService {
	@Autowired
	PrescriptionHistoryMapper prescriptionHistoryMapper;//"yyyy-MM-dd",
	SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.US);

	@Override
	public List<PrescriptionHistory> getAllPrescriptionHistory(Integer pageNo,Integer pageSize,
			Integer auditCenterId,Integer doctorId,Integer auditPharmacistId,String patientName,Integer typeId,
			String problemCode,Integer diagnosisId,Integer stateId,
			 Integer reviewStateId,Integer deptId, String beginDateScope) {
		// TODO Auto-generated method stub
		String startBeginDate = null;
		String endBeginDate = null;
      if (beginDateScope != null && beginDateScope.contains(",")) {
		    try {
		        String[] split = beginDateScope.split(",");
		        startBeginDate = split[0];
		        endBeginDate = split[1];
			} catch (Exception e) {
				// TODO: handle exception
			}
      }
      return prescriptionHistoryMapper.getAllPrescriptionHistory(pageNo,pageSize,auditCenterId,doctorId,auditPharmacistId,patientName,typeId,problemCode,diagnosisId,stateId,reviewStateId,deptId,startBeginDate,endBeginDate);
	}

	@Override
	public int getPrescriptionHistoryCount(Integer auditCenterId,Integer doctorId,Integer auditPharmacistId,String patientName,
			Integer typeId,String problemCode,Integer diagnosisId,
			Integer stateId,Integer reviewStateId,Integer deptId,String beginDateScope) {
		// TODO Auto-generated method stub
		String startBeginDate = null;
		String endBeginDate = null;
      if (beginDateScope != null && beginDateScope.contains(",")) {
		    try {
		        String[] split = beginDateScope.split(",");
		        startBeginDate = split[0];
		        endBeginDate = split[1];
			} catch (Exception e) {
				// TODO: handle exception
			}
      }
		return prescriptionHistoryMapper.getPrescriptionHistoryCount(auditCenterId,doctorId,auditPharmacistId,patientName,typeId,problemCode,diagnosisId,stateId,reviewStateId,deptId,startBeginDate,endBeginDate);
	}

	@Override
	public List<PrescriptionHistorySee> getAllPrescriptionHistorySeeT(Integer pageNo,Integer pageSize,Integer auditCenterId, Integer reviewStateId,
			String prescriptionNumber, String beginDateScope) throws IOException {
		String startBeginDate = null;
		String endBeginDate = null;
      if (beginDateScope != null && beginDateScope.contains(",")) {
		    try {
		        String[] split = beginDateScope.split(",");
		        startBeginDate = split[0];
		        endBeginDate = split[1];
			} catch (Exception e) {
				// TODO: handle exception
			}
      	}
	   return prescriptionHistoryMapper.getAllPrescriptionHistorySeeT(pageNo,pageSize,auditCenterId, reviewStateId, prescriptionNumber, startBeginDate, endBeginDate);

	}

	@Override
	public List<PrescriptionHistorySee> getAllPrescriptionHistorySeeF(Integer pageNo,Integer pageSize,Integer auditCenterId, Integer reviewStateId,
			String prescriptionNumber, String beginDateScope) {
		// TODO Auto-generated method stub
		String startBeginDate = null;
		String endBeginDate = null;
      if (beginDateScope != null && beginDateScope.contains(",")) {
		    try {
		        String[] split = beginDateScope.split(",");
		        startBeginDate = split[0];
		        endBeginDate = split[1];
			} catch (Exception e) {
				// TODO: handle exception
			}
      }
		return prescriptionHistoryMapper.getAllPrescriptionHistorySeeF(pageNo,pageSize,auditCenterId, reviewStateId, prescriptionNumber, startBeginDate, endBeginDate);
	}

	@Override
	public int getPrescriptionHistoryCountF(Integer auditCenterId, Integer reviewStateId,
			String prescriptionNumber, String beginDateScope) {
		// TODO Auto-generated method stub
		String startBeginDate = null;
		String endBeginDate = null;
      if (beginDateScope != null && beginDateScope.contains(",")) {
		    try {
		        String[] split = beginDateScope.split(",");
		        startBeginDate = split[0];
		        endBeginDate = split[1];
			} catch (Exception e) {
				// TODO: handle exception
			}
      }
		return prescriptionHistoryMapper.getPrescriptionHistoryCountF(auditCenterId, reviewStateId, prescriptionNumber, startBeginDate, endBeginDate);
	}

	@Override
	public int getPrescriptionHistoryCountT(Integer auditCenterId, Integer reviewStateId, String prescriptionNumber,
			String beginDateScope) {
		String startBeginDate = null;
		String endBeginDate = null;
      if (beginDateScope != null && beginDateScope.contains(",")) {
         try {
             String[] split = beginDateScope.split(",");
             startBeginDate = split[0];
             endBeginDate = split[1];
		} catch (Exception e) {
			// TODO: handle exception
		}
      }
		// TODO Auto-generated method stub
		return prescriptionHistoryMapper.getPrescriptionHistoryCountT(auditCenterId, reviewStateId, prescriptionNumber, startBeginDate, endBeginDate);
	}

	@Override
	public List<PrescriptionHistoryPending> getPendingHistory(Integer pageNo, Integer pageSize,
			Integer auditCenterId,Integer prescriptionNumber, Integer reviewStateId, String typeId, String beginDateScope) {
		// TODO Auto-generated method stub
		String startBeginDate = null;
		String endBeginDate = null;
      if (beginDateScope != null && beginDateScope.contains(",")) {
         try {
             String[] split = beginDateScope.split(",");
             startBeginDate = split[0];
             endBeginDate = split[1];
		} catch (Exception e) {
			// TODO: handle exception
		}
      }
		return prescriptionHistoryMapper.getPendingHistory(pageNo, pageSize, auditCenterId,prescriptionNumber, reviewStateId, typeId, startBeginDate, endBeginDate);
	}

	@Override
	public int getPendingHistoryCount(Integer auditCenterId,Integer prescriptionNumber, Integer reviewStateId, String typeId,
			String beginDateScope) {
		// TODO Auto-generated method stub
		String startBeginDate = null;
		String endBeginDate = null;
      if (beginDateScope != null && beginDateScope.contains(",")) {
         try {
             String[] split = beginDateScope.split(",");
             startBeginDate = split[0];
             endBeginDate = split[1];
		} catch (Exception e) {
			// TODO: handle exception
		}
      }
		return prescriptionHistoryMapper.getPendingHistoryCount(auditCenterId,prescriptionNumber, reviewStateId, typeId, startBeginDate, endBeginDate);
	}

	@Override
	public List<PrescriptionHistoryDetail> getPreHisById(Integer id) throws IOException {
		// TODO Auto-generated method stub
		try {
			return prescriptionHistoryMapper.getPreHisById(id);
		} catch (Exception e) {
			// TODO: handle exception
//			log.info(e.printStackTrace());
			log.error("exception toString and track space : {}", "\r\n" + e);
			log.error("---------------------------------------------");
			e.printStackTrace();

			return null;
		}
		
	}

	@Override
	public List<PrescriptionHistoryAndPatient> getPrescriptionHistoryAndPatient(String idNumber,
                                                                                String socialSecurityNumber, Timestamp nowDate, Timestamp beforeDate,
                                                                                Timestamp dTimeBefore, Timestamp dTimeLater ) {
		// TODO Auto-generated method stub
		return prescriptionHistoryMapper.getPrescriptionHistoryAndPatient(idNumber, socialSecurityNumber,nowDate,beforeDate,dTimeBefore,dTimeLater);
	}

	@Override
	public int getHistoryCheckDays() {
		// TODO Auto-generated method stub
		return prescriptionHistoryMapper.getHistoryCheckDays();
	}

	@Override
	public int getCountsByIdNumber(String idNumber, String prescriptionNumber) {
		// TODO Auto-generated method stub
		return prescriptionHistoryMapper.getCountsByIdNumber(idNumber, prescriptionNumber);
	}

	@Override
	public int getCountsBySocialSecurityNumber(String socialSecurityNumber, String prescriptionNumber) {
		// TODO Auto-generated method stub
		return prescriptionHistoryMapper.getCountsBySocialSecurityNumber(socialSecurityNumber, prescriptionNumber);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.PrescriptionHistoryService#getReportSummary(java.lang.String)
	 */
	@Override
	public List<reportSummary> getReportSummary(String beginDateScope) {
		// TODO Auto-generated method stub
		String startBeginDate = null;
		String endBeginDate = null;
      if (beginDateScope != null && beginDateScope.contains(",")) {
         try {
             String[] split = beginDateScope.split(",");
             startBeginDate = split[0];
             endBeginDate = split[1];
		} catch (Exception e) {
		}
      }
      return prescriptionHistoryMapper.getReportSummary(startBeginDate,endBeginDate);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.PrescriptionHistoryService#getReportSummaryCount(java.lang.String)
	 */
	@Override
	public int getReportSummaryCount(String beginDateScope) {
		// TODO Auto-generated method stub
		String startBeginDate = null;
		String endBeginDate = null;
      if (beginDateScope != null && beginDateScope.contains(",")) {
         try {
             String[] split = beginDateScope.split(",");
             startBeginDate = split[0];
             endBeginDate = split[1];
		} catch (Exception e) {
		}
      }
		return prescriptionHistoryMapper.getReportSummaryCount(startBeginDate, endBeginDate);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.PrescriptionHistoryService#getReportSummaryDetail(java.lang.String)
	 */
	@Override
	public List<PrescriptionHistoryReportDetail> getReportSummaryDetail(Integer pageNo, Integer pageSize, String beginDateScope) {
		// TODO Auto-generated method stub
		String startBeginDate = null;
		String endBeginDate = null;
      if (beginDateScope != null && beginDateScope.contains(",")) {
         try {
             String[] split = beginDateScope.split(",");
             startBeginDate = split[0];
             endBeginDate = split[1];
		} catch (Exception e) {
		}
      }
		return prescriptionHistoryMapper.getReportSummaryDetail(pageNo,pageSize,startBeginDate,endBeginDate);
	}

	/* (non-Javadoc)
	 * @see com.ck.springboot.service.PrescriptionHistoryService#getProblemById(java.lang.String)
	 */
	@Override
	public List<ProblemCode> getProblemById(String prescriptionNumber) {
		// TODO Auto-generated method stub
		return prescriptionHistoryMapper.getProblemById(prescriptionNumber);
	}
}
