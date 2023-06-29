// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** register POST /api/register */
export async function registerUsingPOST1(
  body: API.RegisterRequest,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/register', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** sendCode GET /api/sendCode */
export async function sendCodeUsingGET1(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.sendCodeUsingGET1Params,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/sendCode', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
