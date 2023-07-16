// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** updateUser PUT /api/user */
export async function updateUserUsingPUT(
  body: API.UserUpdateRequest,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/user', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** addUser POST /api/user */
export async function addUserUsingPOST(
  body: API.UserAddRequest,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/user', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getUserInfo GET /api/user/${param0} */
export async function getUserInfoUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUserInfoUsingGETParams,
  options?: { [key: string]: any },
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api/user/${param0}`, {
    method: 'GET',
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** deleteUser DELETE /api/user/${param0} */
export async function deleteUserUsingDELETE(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteUserUsingDELETEParams,
  options?: { [key: string]: any },
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api/user/${param0}`, {
    method: 'DELETE',
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** getUserListVoByPage POST /api/user/all/vo/page */
export async function getUserListVoByPageUsingPOST(
  body: API.UserQueryRequest,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/user/all/vo/page', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** applyUserApiToken GET /api/user/applyToken */
export async function applyUserApiTokenUsingGET(options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/user/applyToken', {
    method: 'GET',
    ...(options || {}),
  });
}

/** getCurrentUser GET /api/user/current */
export async function getCurrentUserUsingGET(options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/user/current', {
    method: 'GET',
    ...(options || {}),
  });
}
