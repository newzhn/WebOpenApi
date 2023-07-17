import {PlusOutlined, QuestionCircleOutlined} from '@ant-design/icons';
import type {ActionType, ProColumns, ProDescriptionsItemProps} from '@ant-design/pro-components';
import {
  PageContainer,
  ProDescriptions,
  ProTable,
} from '@ant-design/pro-components';
import '@umijs/max';
import {Button, Drawer, message, Popconfirm} from 'antd';
import React, {useRef, useState} from 'react';
import CreateModal from "@/pages/Admin/Interface/components/CreateModal";
import UpdateModal from "@/pages/Admin/Interface/components/UpdateModal";
import {
  addInterfaceUsingPOST, deleteInterfaceByIdsUsingDELETE,
  getInterfaceListVoByPageUsingPOST, offlineInterfaceUsingPUT, onlineInterfaceUsingPUT, updateInterfaceUsingPUT
} from "@/services/WebOpenApi-backend/interfaceController";
import ShowModal from "@/pages/Admin/Interface/components/ShowModal";

const InterfaceInfo: React.FC = () => {
  const [createModalOpen, handleModalOpen] = useState<boolean>(false);
  const [updateModalOpen, handleUpdateModalOpen] = useState<boolean>(false);
  const [showModalOpen, handleShowModalOpen] = useState<boolean>(false);
  const [showDetail, setShowDetail] = useState<boolean>(false);
  const [currentRow, setCurrentRow] = useState<API.InterfaceInfoVo>();
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
    const res = await getInterfaceListVoByPageUsingPOST({
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
  const handleAdd = async (fields: API.InterfaceAddRequest) => {
    const hide = message.loading('正在添加');
    try {
      await addInterfaceUsingPOST({
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
  const handleUpdate = async (fields: API.InterfaceUpdateRequest) => {
    const hide = message.loading('修改中');
    try {
      await updateInterfaceUsingPUT({
        ...fields
      });
      hide();
      message.success('接口修改成功');
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
  const handleRemove = async (selectedRows: API.InterfaceInfoVo) => {
    const hide = message.loading('正在删除');
    if (!selectedRows) return true;
    try {
      await deleteInterfaceByIdsUsingDELETE({
        ids: [ selectedRows.id ]
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
   *  online node
   * @zh-CN 上线接口
   *
   * @param record
   */
  const handleOnline = async (record: API.IdRequest) => {
    const hide = message.loading('正在上线中');
    if (!record) return true;
    try {
      await onlineInterfaceUsingPUT({
        id: record.id
      });
      hide();
      message.success('上线成功');
      actionRef.current?.reload();
      return true;
    } catch (error) {
      hide();
      return false;
    }
  };

  /**
   *  offline node
   * @zh-CN 下线接口
   *
   * @param record
   */
  const handleOffline = async (record: API.IdRequest) => {
    const hide = message.loading('正在下线中');
    if (!record) return true;
    try {
      await offlineInterfaceUsingPUT({
        id: record.id
      });
      hide();
      message.success('下线成功');
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
  const columns: ProColumns<API.InterfaceInfoVo>[] = [
    {
      title: 'id',
      dataIndex: 'id',
      valueType: 'index',
    },
    {
      title: '接口名称',
      dataIndex: 'name',
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
      title: '描述',
      dataIndex: 'description',
      valueType: 'textarea',
      formItemProps: {
        rules: [
          {
            required: true,
          },
        ],
      },
    },
    {
      title: '请求方法',
      dataIndex: 'method',
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
      title: '接口地址',
      dataIndex: 'host',
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
      title: '接口路径',
      dataIndex: 'url',
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
      title: '请求参数',
      dataIndex: 'requestParams',
      valueType: 'jsonCode',
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
      title: '接口申请次数',
      dataIndex: 'applyNum',
      valueType: 'text',
      hideInTable: true,
      hideInSearch: true,
    },
    {
      title: '请求头',
      dataIndex: 'requestHeader',
      valueType: 'jsonCode',
      hideInTable: true,
      hideInSearch: true,
    },
    {
      title: '响应头',
      dataIndex: 'responseHeader',
      valueType: 'jsonCode',
      hideInTable: true,
      hideInSearch: true,
    },
    {
      title: '状态',
      dataIndex: 'status',
      hideInForm: true,
      valueEnum: {
        0: {
          text: '已下线',
          status: 'Default',
        },
        1: {
          text: '在线',
          status: 'Processing',
        },
      },
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      valueType: 'dateTime',
      hideInForm: true,
      hideInSearch: true,
    },
    {
      title: '更新时间',
      dataIndex: 'updateTime',
      valueType: 'dateTime',
      hideInForm: true,
      hideInTable: true,
      hideInSearch: true,
    },
    {
      title: '操作',
      dataIndex: 'option',
      valueType: 'option',
      render: (_, record) => {
        return record.status === 0
          ? [
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
                setCurrentRow(record);
              }}
            >
              修改
            </Button>,
            <Button
              key="online"
              onClick={() => {
                handleOnline(record);
              }}
            >
              发布
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
          : [
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
                setCurrentRow(record);
              }}
            >
              修改
            </Button>,
            <Button
              key="online"
              onClick={() => {
                handleOffline(record);
              }}
            >
              下线
            </Button>,
            <Popconfirm
              title="删除数据"
              key="remove"
              description="确认删除该数据吗？"
              icon={<QuestionCircleOutlined style={{ color: 'red' }} />}
              onConfirm={() => {
                handleRemove(record);
              }}
            >
              <Button danger>删除</Button>
            </Popconfirm>,
          ];
      },
    },
  ];

  /**
   * 请求参数说明展示列
   */
  const requestColumns: ProColumns<API.RequestParamsRemarkVO>[] = [
    {
      title: '名称',
      dataIndex: 'name',
      width: '15%',
    },
    {
      title: '必填',
      key: 'isRequired',
      dataIndex: 'isRequired',
      valueType: 'select',
      valueEnum: {
        yes: {
          text: '是',
        },
        no: {
          text: '否',
        },
      },
      width: '15%',
    },
    {
      title: '类型',
      dataIndex: 'type',
      width: '15%',
    },
    {
      title: '说明',
      dataIndex: 'remark',
    },
    {
      title: '操作',
      valueType: 'option',
      width: '10%',
      render: () => {
        return null;
      },
    },
  ];

  /**
   * 响应参数说明展示列
   */
  const responseColumns: ProColumns<API.RequestParamsRemarkVO>[] = [
    {
      title: '名称',
      dataIndex: 'name',
      width: '15%',
    },
    {
      title: '类型',
      dataIndex: 'type',
      width: '15%',
    },
    {
      title: '说明',
      dataIndex: 'remark',
    },
    {
      title: '操作',
      valueType: 'option',
      width: '10%',
      render: () => {
        return null;
      },
    },
  ];

  return (
    <PageContainer>
      <ProTable<API.InterfaceInfoVo, API.PageParams>
        headerTitle={'查询表格'}
        actionRef={actionRef}
        rowKey="key"
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
          value.id = currentRow.id
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
        requestColumns={requestColumns}
        responseColumns={responseColumns}
      />

      <Drawer
        width={600}
        open={showDetail}
        onClose={() => {
          setCurrentRow(undefined);
          setShowDetail(false);
        }}
        closable={false}
      >
        {currentRow?.name && (
          <ProDescriptions<API.RuleListItem>
            column={2}
            title={currentRow?.name}
            request={async () => ({
              data: currentRow || {},
            })}
            params={{
              id: currentRow?.name,
            }}
            columns={columns as ProDescriptionsItemProps<API.RuleListItem>[]}
          />
        )}
      </Drawer>

      <ShowModal
        setVisible={handleShowModalOpen}
        values={currentRow ?? {}}
        visible={showModalOpen}
        requestColumns={requestColumns}
        responseColumns={responseColumns}
      />

      <CreateModal
        columns={columns}
        setVisible={handleModalOpen}
        onSubmit={(values) => {
          return handleAdd(values).then((r) => {});
        }}
        visible={createModalOpen}
        requestColumns={requestColumns}
        responseColumns={responseColumns}
      />
    </PageContainer>
  );
};
export default InterfaceInfo;
