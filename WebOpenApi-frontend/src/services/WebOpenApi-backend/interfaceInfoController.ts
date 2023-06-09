// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** updateInterface PUT /api/interface */
export async function updateInterfaceUsingPUT(
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

/** addInterface POST /api/interface */
export async function addInterfaceUsingPOST(
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

/** deleteInterfaceByIds DELETE /api/interface/${param0} */
export async function deleteInterfaceByIdsUsingDELETE(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteInterfaceByIdsUsingDELETEParams,
  options?: { [key: string]: any },
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api/interface/${param0}`, {
    method: 'DELETE',
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** getInterfaceInfo GET /api/interface/${param0} */
export async function getInterfaceInfoUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getInterfaceInfoUsingGETParams,
  options?: { [key: string]: any },
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api/interface/${param0}`, {
    method: 'GET',
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** invokeInterfaceInfo POST /api/interface/invoke */
export async function invokeInterfaceInfoUsingPOST(
  body: API.InterfaceInfoInvokeRequest,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/interface/invoke', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getInterfaceListVoByPage POST /api/interface/list/page/vo */
export async function getInterfaceListVoByPageUsingPOST(
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

/** offlineInterfaceInfo PUT /api/interface/offline */
export async function offlineInterfaceInfoUsingPUT(
  body: API.IdRequest,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/interface/offline', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** onlineInterfaceInfo PUT /api/interface/online */
export async function onlineInterfaceInfoUsingPUT(
  body: API.IdRequest,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/interface/online', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
