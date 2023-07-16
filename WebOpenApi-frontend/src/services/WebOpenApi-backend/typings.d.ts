declare namespace API {
  type deleteInterfaceByIdsUsingDELETEParams = {
    /** ids */
    ids: number[];
  };

  type deleteUserUsingDELETEParams = {
    /** id */
    id: number;
  };

  type getDetailInterfaceInfoUsingGETParams = {
    /** id */
    id: number;
  };

  type getInterfaceInfoUsingGETParams = {
    /** id */
    id: number;
  };

  type getInterfaceMeVoListUsingGETParams = {
    /** search */
    search: string;
  };

  type getUserInfoUsingGETParams = {
    /** id */
    id: number;
  };

  type IdRequest = {
    id: number;
  };

  type InterfaceAddRequest = {
    applyNum?: number;
    description: string;
    host: string;
    method: string;
    name: string;
    requestHeader: string;
    requestParams: string;
    responseHeader: string;
    uri: string;
  };

  type InterfaceApplyRequest = {
    applyNum?: number;
    interfaceInfoId: number;
  };

  type InterfaceInvokeRequest = {
    id: number;
    userRequestParams: string;
  };

  type InterfaceQueryRequest = {
    current?: number;
    method?: string;
    name?: string;
    pageSize?: number;
    status?: number;
    uri?: string;
  };

  type InterfaceUpdateRequest = {
    applyNum: number;
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
    email: string;
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

  type UserVo = {
    id?: number;
    userAccount?: string;
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
    uri: string;
    applyNum: number;
    requestParams: string;
    requestHeader: string;
    responseHeader: string;
    status: number;
    userId: number;
    createBy: string;
    createTime: Date;
    updateTime?: Date;
  };

  /**
   * 接口封装对象
   */
  type InterfaceDetailVo = {
    id: number;
    name: string;
    description: string;
    method: string;
    uri: string;
    surplusNum: number;
    applyNum: number;
    requestParams: string;
    requestHeader: string;
    responseHeader: string;
    status: number;
    userId: number;
    createBy: string;
    createTime: Date;
    updateTime?: Date;
  };

  /**
   * 接口排行榜对象
   */
  type InterfaceRankVo = {
    id: number;
    name: string;
    num: number;
  }

  /**
   * 接口Me对象
   */
  type InterfaceMeVo = {
    id: number;
    name: string;
    description: string;
    method: string;
    uri: string;
    surplusNum: number;
    status: number;
    createTime: Date;
  };

  /**
   * 接口商店对象
   */
  type InterfaceStoreVo = {
    id: number;
    name: string;
    description: string;
    method: string;
    uri: string;
    applyNum: number;
    applyFlag: boolean;
    status: number;
    createTime: Date;
  };
}
