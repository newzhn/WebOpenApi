import {PlusOutlined} from '@ant-design/icons';
import type {ActionType, ProColumns} from '@ant-design/pro-components';
import {
  PageContainer,
  ProTable,
} from '@ant-design/pro-components';
import '@umijs/max';
import {Avatar, Button, message} from 'antd';
import React, {useRef, useState} from 'react';
import CreateModal from "@/pages/Admin/User/components/CreateModal";
import UpdateModal from "@/pages/Admin/User/components/UpdateModal";
import ShowModal from "@/pages/Admin/User/components/ShowModal";
import {
  addUserUsingPOST, deleteUserUsingDELETE,
  getUserListVoByPageUsingPOST,
  updateUserUsingPUT
} from "@/services/WebOpenApi-backend/userController";

const InterfaceInfo: React.FC = () => {
  const [createModalOpen, handleModalOpen] = useState<boolean>(false);
  const [updateModalOpen, handleUpdateModalOpen] = useState<boolean>(false);
  const [showModalOpen, handleShowModalOpen] = useState<boolean>(false);
  const [currentRow, setCurrentRow] = useState<API.UserVo>();
  const actionRef = useRef<ActionType>();

  /**
   * 获取接口分页数据
   * @param params
   */
  const getData = async (
    params: {
      // query
      /** 当前的页码 */
      current?: number;
      /** 页面的容量 */
      pageSize?: number;
    },
  ) => {
    const res = await getUserListVoByPageUsingPOST({
      ...params
    })
    if (res?.data) {
      return {
        data: res.data.records || [],
        success: true,
        total: res.data.total || 0
      }
    } else {
      return {
        data: [],
        success: false,
        total: 0
      }
    }
  }

  /**
   * @en-US Add node
   * @zh-CN 添加节点
   * @param fields
   */
  const handleAdd = async (fields: API.UserAddRequest) => {
    const hide = message.loading('正在添加');
    try {
      await addUserUsingPOST({
        ...fields,
      });
      hide();
      message.success('添加成功');
      handleModalOpen(false);
      actionRef.current?.reload();
      return true;
    } catch (error: any) {
      hide();
      return false;
    }
  };

  /**
   * @en-US Update node
   * @zh-CN 更新节点
   *
   * @param fields
   */
  const handleUpdate = async (fields: API.UserUpdateRequest) => {
    const hide = message.loading('修改中');
    try {
      await updateUserUsingPUT({
        ...fields
      });
      hide();
      message.success('用户修改成功');
      actionRef.current?.reload();
      return true;
    } catch (error) {
      hide();
      return false;
    }
  };

  /**
   *  Delete node
   * @zh-CN 删除节点
   *
   * @param selectedRows
   */
  const handleRemove = async (selectedRows: API.UserVo) => {
    const hide = message.loading('正在删除');
    if (!selectedRows) return true;
    try {
      await deleteUserUsingDELETE({
        id: Number(selectedRows.id)
      });
      hide();
      message.success('删除成功');
      actionRef.current?.reload();
      return true;
    } catch (error) {
      hide();
      return false;
    }
  };

  /**
   * table 展示的列
   * */
  const columns: ProColumns<API.UserVo>[] = [
    {
      title: 'id',
      dataIndex: 'id',
      valueType: 'text',
    },
    {
      title: '账户名',
      dataIndex: 'userAccount',
      valueType: 'text',
      formItemProps: {
        rules: [
          {
            required: true,
          },
        ],
      },
    },
    {
      title: '昵称',
      dataIndex: 'userName',
      valueType: 'text',
      hideInSearch: true,
      formItemProps: {
        rules: [
          {
            required: true,
          },
        ],
      },
    },
    {
      title: '头像',
      dataIndex: 'userAvatar',
      valueType: 'text',
      hideInSearch: true,
      formItemProps: {
        rules: [
          {
            required: true,
          },
        ],
      },
      render: (_, record) => {
        return <Avatar shape="square" size="large" src={<img src={record.userAvatar} alt="avatar" />} />
      },
    },
    {
      title: '简介',
      dataIndex: 'userProfile',
      valueType: 'textarea',
      hideInTable: true,
      hideInSearch: true,
      formItemProps: {
        rules: [
          {
            required: true,
          },
        ],
      },
    },
    {
      title: '邮箱',
      dataIndex: 'email',
      valueType: 'text',
      hideInTable: true,
      formItemProps: {
        rules: [
          {
            required: true,
          },
        ],
      },
    },
    {
      title: 'QQ',
      dataIndex: 'qq',
      valueType: 'text',
      hideInTable: true,
      formItemProps: {
        rules: [
          {
            required: true,
          },
        ],
      },
    },
    {
      title: '角色',
      dataIndex: 'userRole',
      valueType: 'text',
      formItemProps: {
        rules: [
          {
            required: true,
          },
        ],
      },
    },
    {
      title: 'accessKey',
      dataIndex: 'accessKey',
      valueType: 'text',
      hideInTable: true,
      hideInSearch: true,
      formItemProps: {
        rules: [
          {
            required: true,
          },
        ],
      },
    },
    {
      title: 'secretKey',
      dataIndex: 'accessKey',
      valueType: 'text',
      hideInTable: true,
      hideInSearch: true,
      formItemProps: {
        rules: [
          {
            required: true,
          },
        ],
      },
    },
    {
      title: '注册时间',
      dataIndex: 'createTime',
      valueType: 'dateTime',
      hideInForm: true,
      hideInSearch: true,
    },
    {
      title: '操作',
      dataIndex: 'option',
      valueType: 'option',
      render: (_, record) => {
        return [
          <Button
            key="detail"
            onClick={() => {
              handleShowModalOpen(true);
              setCurrentRow(record);
            }}
          >
            详情
          </Button>,
          <Button
            key="update"
            onClick={() => {
              handleUpdateModalOpen(true);
              console.log(record)
              setCurrentRow(record);
            }}
          >
            修改
          </Button>,
          <Button
            danger
            key="remove"
            onClick={() => {
              handleRemove(record);
            }}
          >
            删除
          </Button>,
        ]
      },
    },
  ];

  return (
    <PageContainer>
      <ProTable<API.UserVo, API.PageParams>
        headerTitle={'查询表格'}
        actionRef={actionRef}
        rowKey="id"
        search={{
          labelWidth: 120,
        }}
        toolBarRender={() => [
          <Button
            type="primary"
            key="primary"
            onClick={() => {
              handleModalOpen(true);
            }}
          >
            <PlusOutlined /> 新建
          </Button>,
        ]}
        request={getData}
        columns={columns}
      />

      <UpdateModal
        columns={columns}
        onSubmit={async (value) => {
          // @ts-ignore
          console.log(value)
          value.id = currentRow?.id
          console.log(value)
          const success = await handleUpdate(value);
          if (success) {
            handleUpdateModalOpen(false);
            setCurrentRow(undefined);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
        setVisible={handleUpdateModalOpen}
        visible={updateModalOpen}
        values={currentRow ?? {}}
      />

      <ShowModal
        setVisible={handleShowModalOpen}
        values={currentRow ?? {}}
        visible={showModalOpen}
      />

      <CreateModal
        columns={columns}
        setVisible={handleModalOpen}
        onSubmit={(values) => {
          return handleAdd(values).then((r) => {});
        }}
        visible={createModalOpen}
      />
    </PageContainer>
  );
};
export default InterfaceInfo;
