import fetch from '../utils/fetch'
import apiUrls from './api'

const {
  getreportsummary, // GET 查询处方审核汇总表
  getreportsummarydetail // GET 查询处方审核明细表
} = apiUrls.report

// 审核报表 查询处方审核汇总表
export async function GetReportSummary(args) {
  const res = await fetch(getreportsummary, args, 'GET')
  return res
}
// 审核报表 查询处方审核明细表
export async function GetReportSummaryDetail(args) {
  const res = await fetch(getreportsummarydetail, args, 'GET')
  return res
}
