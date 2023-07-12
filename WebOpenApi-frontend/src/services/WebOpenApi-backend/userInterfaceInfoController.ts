// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** updateUserInterface PUT /api/user_interface */
export async function updateUserInterfaceUsingPUT(
  body: API.UserInterfaceInfoUpdateRequest,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/user_interface', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** addUserInterface POST /api/user_interface */
export async function addUserInterfaceUsingPOST(
  body: API.UserInterfaceInfoAddRequest,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/user_interface', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** deleteUserInterfaceByIds DELETE /api/user_interface/${param0} */
export async function deleteUserInterfaceByIdsUsingDELETE(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteUserInterfaceByIdsUsingDELETEParams,
  options?: { [key: string]: any },
) {
  const { ids: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api/user_interface/${param0}`, {
    method: 'DELETE',
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** getUserInterfaceInfo GET /api/user_interface/${param0} */
export async function getUserInterfaceInfoUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUserInterfaceInfoUsingGETParams,
  options?: { [key: string]: any },
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api/user_interface/${param0}`, {
    method: 'GET',
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** getUserInterfaceListVoByPage POST /api/user_interface/list/page/vo */
export async function getUserInterfaceListVoByPageUsingPOST(
  body: API.UserInterfaceInfoQueryRequest,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/user_interface/list/page/vo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
