declare namespace API {
  type deleteInterfaceByIdsUsingDELETEParams = {
    /** ids */
    ids: number[];
  };

  type deleteUserInterfaceByIdsUsingDELETEParams = {
    /** ids */
    ids: number[];
  };

  type deleteUserUsingDELETEParams = {
    /** id */
    id: number;
  };

  type getInterfaceInfoUsingGETParams = {
    /** id */
    id: number;
  };

  type getUserInfoUsingGETParams = {
    /** id */
    id: number;
  };

  type getUserInterfaceInfoUsingGETParams = {
    /** id */
    id: number;
  };

  type IdRequest = {
    id: number;
  };

  type InterfaceInfoAddRequest = {
    description: string;
    host: string;
    method: string;
    name: string;
    requestHeader: string;
    requestParams: string;
    responseHeader: string;
    uri: string;
  };

  type InterfaceInfoInvokeRequest = {
    id: number;
    userRequestParams: string;
  };

  type InterfaceInfoQueryRequest = {
    current?: number;
    method?: string;
    name?: string;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
    status?: number;
    uri?: string;
  };

  type InterfaceInfoUpdateRequest = {
    description: string;
    host: string;
    id: number;
    method: string;
    name: string;
    requestHeader: string;
    requestParams: string;
    responseHeader: string;
    uri: string;
  };

  type LoginRequest = {
    userAccount: string;
    userPassword: string;
  };

  type RegisterRequest = {
    email: string;
    qq: string;
    userAccount: string;
    userPassword: string;
    verificationCode: string;
  };

  type sendCodeUsingGETParams = {
    /** email */
    email?: string;
  };

  type UserAddRequest = {
    email: string;
    qq: string;
    userAccount: string;
    userPassword: string;
    userProfile?: string;
    userRole?: string;
  };

  type UserInterfaceInfoAddRequest = {
    interfaceInfoId: number;
  };

  type UserInterfaceInfoQueryRequest = {
    current?: number;
    id?: number;
    interfaceInfoId?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
    status?: number;
    userId?: number;
  };

  type UserInterfaceInfoUpdateRequest = {
    id: number;
    status?: number;
    surplusNum?: number;
    totalNum?: number;
  };

  type UserQueryRequest = {
    current?: number;
    email?: string;
    pageSize?: number;
    qq?: string;
    sortField?: string;
    sortOrder?: string;
    userAccount?: string;
    userRole?: string;
  };

  type UserUpdateRequest = {
    email?: string;
    id: number;
    qq?: string;
    userAvatar?: string;
    userName?: string;
    userPassword?: string;
    userProfile?: string;
    userRole?: string;
  };

  type User = {
    id?: number;
    userAccount?: string;
    userPassword?: string;
    userName?: string;
    userAvatar?: string;
    userProfile?: string;
    email?: string;
    qq?: string;
    userRole?: string;
    accessKey?: string;
    secretKey?: string;
    createTime?: Date;
    updateTime?: Date;
    isDelete?: number;
  };

  type LoginUser = {
    user: {
      id?: number;
      userAccount?: string;
      userPassword?: string;
      userName?: string;
      userAvatar?: string;
      userProfile?: string;
      email?: string;
      qq?: string;
      userRole?: string;
      accessKey?: string;
      secretKey?: string;
      createTime?: Date;
      updateTime?: Date;
      isDelete?: number;
    };
    permissions?: string[];
  };

  /**
   * 接口封装对象
   */
  type InterfaceInfoVo = {
    id: number;
    name: string;
    description: string;
    method: string;
    host: string;
    uri: string;
    requestParams: string;
    requestHeader: string;
    responseHeader: string;
    status: number;
    userId: number;
    createBy: string;
    createTime: Date;
    updateTime?: Date;
  };
}
