// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** update PUT /api/interface */
export async function updateUsingPUT2(
  body: API.InterfaceInfoUpdateRequest,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/interface', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** add POST /api/interface */
export async function addUsingPOST2(
  body: API.InterfaceInfoAddRequest,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/interface', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getInfo GET /api/interface/${param0} */
export async function getInfoUsingGET2(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getInfoUsingGET2Params,
  options?: { [key: string]: any },
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api/interface/${param0}`, {
    method: 'GET',
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** delete DELETE /api/interface/${param0} */
export async function deleteUsingDELETE2(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteUsingDELETE2Params,
  options?: { [key: string]: any },
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api/interface/${param0}`, {
    method: 'DELETE',
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** getListVoByPage POST /api/interface/list/page/vo */
export async function getListVoByPageUsingPOST2(
  body: API.InterfaceInfoQueryRequest,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/interface/list/page/vo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
