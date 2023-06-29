// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** login POST /api/login */
export async function loginUsingPOST(body: API.LoginRequest, options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** logout GET /api/quit */
export async function logoutUsingGET(options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/quit', {
    method: 'GET',
    ...(options || {}),
  });
}
