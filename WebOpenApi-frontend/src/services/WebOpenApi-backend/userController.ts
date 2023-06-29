// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** update PUT /api/user */
export async function updateUsingPUT3(
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

/** add POST /api/user */
export async function addUsingPOST3(body: API.UserAddRequest, options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/user', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getInfo GET /api/user/${param0} */
export async function getInfoUsingGET3(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getInfoUsingGET3Params,
  options?: { [key: string]: any },
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api/user/${param0}`, {
    method: 'GET',
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** delete DELETE /api/user/${param0} */
export async function deleteUsingDELETE3(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteUsingDELETE3Params,
  options?: { [key: string]: any },
) {
  const { id: param0, ...queryParams } = params;
  return request<Record<string, any>>(`/api/user/${param0}`, {
    method: 'DELETE',
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** getCurrentUser GET /api/user/current */
export async function getCurrentUserUsingGET1(options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/user/current', {
    method: 'GET',
    ...(options || {}),
  });
}

/** getListVoByPage POST /api/user/list/page/vo */
export async function getListVoByPageUsingPOST3(
  body: API.UserQueryRequest,
  options?: { [key: string]: any },
) {
  return request<Record<string, any>>('/api/user/list/page/vo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
