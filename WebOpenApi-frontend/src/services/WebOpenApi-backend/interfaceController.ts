// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** updateInterface PUT /api/interface */
export async function updateInterfaceUsingPUT(
  body: API.InterfaceUpdateRequest,
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
  body: API.InterfaceAddRequest,
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

/** getInterfaceListVoByPage POST /api/interface/all/vo/page */
export async function getInterfaceListVoByPageUsingPOST(
  body: API.InterfaceQueryRequest,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/interface/all/vo/page', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** applyInterface POST /api/interface/apply */
export async function applyInterfaceUsingPOST(
  body: API.InterfaceApplyRequest,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/interface/apply', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** invokeInterface POST /api/interface/invoke */
export async function invokeInterfaceUsingPOST(
  body: API.InterfaceInvokeRequest,
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

/** getInterfaceMeVoList GET /api/interface/me/vo/list */
export async function getInterfaceMeVoListUsingGET(options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/interface/me/vo/list', {
    method: 'GET',
    ...(options || {}),
  });
}

/** offlineInterface PUT /api/interface/offline */
export async function offlineInterfaceUsingPUT(
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

/** onlineInterface PUT /api/interface/online */
export async function onlineInterfaceUsingPUT(
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

/** getInterfaceStoreVoByPage POST /api/interface/store/vo/page */
export async function getInterfaceStoreVoByPageUsingPOST(
  body: API.InterfaceQueryRequest,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/interface/store/vo/page', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
