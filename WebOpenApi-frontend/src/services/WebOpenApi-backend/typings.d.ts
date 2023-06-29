declare namespace API {
  type deleteUsingDELETE2Params = {
    /** id */
    id: number;
  };

  type deleteUsingDELETE3Params = {
    /** id */
    id: number;
  };

  type getInfoUsingGET2Params = {
    /** id */
    id: number;
  };

  type getInfoUsingGET3Params = {
    /** id */
    id: number;
  };

  type InterfaceInfoAddRequest = {
    description?: string;
    method?: string;
    name?: string;
    requestHeader?: string;
    responseHeader?: string;
    url?: string;
  };

  type InterfaceInfoQueryRequest = {
    current?: number;
    method?: string;
    name?: string;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
    status?: number;
    url?: string;
  };

  type InterfaceInfoUpdateRequest = {
    description?: string;
    id?: number;
    method?: string;
    name?: string;
    requestHeader?: string;
    responseHeader?: string;
    status?: number;
    url?: string;
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

  type sendCodeUsingGET1Params = {
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
    url: string;
    requestHeader: string;
    responseHeader: string;
    status: number;
    userId: number;
    createBy: string;
    createTime: Date;
  };
}
