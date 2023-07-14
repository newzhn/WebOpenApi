// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** getRankInterfaceInfoVoList GET /api/interface/statistic/rank/vo/list */
export async function getRankInterfaceInfoVoListUsingGET(options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/interface/statistic/rank/vo/list', {
    method: 'GET',
    ...(options || {}),
  });
}
