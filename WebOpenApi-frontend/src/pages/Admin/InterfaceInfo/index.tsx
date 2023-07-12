import {PlusOutlined} from '@ant-design/icons';
import type {ActionType, ProColumns, ProDescriptionsItemProps} from '@ant-design/pro-components';
import {
  FooterToolbar,
  PageContainer,
  ProDescriptions,
  ProTable,
} from '@ant-design/pro-components';
import '@umijs/max';
import {Button, Drawer, message} from 'antd';
import React, {useRef, useState} from 'react';
import {
  addInterfaceUsingPOST, deleteInterfaceByIdsUsingDELETE,
  getInterfaceListVoByPageUsingPOST, offlineInterfaceInfoUsingPUT, onlineInterfaceInfoUsingPUT,
  updateInterfaceUsingPUT
} from "@/services/WebOpenApi-backend/interfaceInfoController";
import CreateModal from "@/pages/Admin/InterfaceInfo/components/CreateModal";
import UpdateModal from "@/pages/Admin/InterfaceInfo/components/UpdateModal";

const InterfaceInfo: React.FC = () => {
  /**
   * @en-US Pop-up window of new window
   * @zh-CN 新建窗口的弹窗
   *  */
  const [createModalOpen, handleModalOpen] = useState<boolean>(false);
  /**
   * @en-US The pop-up window of the distribution update window
   * @zh-CN 分布更新窗口的弹窗
   * */
  const [updateModalOpen, handleUpdateModalOpen] = useState<boolean>(false);
  const [showDetail, setShowDetail] = useState<boolean>(false);
  const actionRef = useRef<ActionType>();
  const [currentRow, setCurrentRow] = useState<API.InterfaceInfoVo>();
  const [selectedRowsState, setSelectedRows] = useState<API.InterfaceInfoVo[]>([]);

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
  const handleAdd = async (fields: API.InterfaceInfoAddRequest) => {
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
  const handleUpdate = async (fields: API.InterfaceInfoUpdateRequest) => {
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
  const handleRemove = async (selectedRows: API.InterfaceInfoVo[]) => {
    const hide = message.loading('正在删除');
    if (!selectedRows) return true;
    try {
      await deleteInterfaceByIdsUsingDELETE({
        ids: selectedRows.map(row => row.id)
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
      await onlineInterfaceInfoUsingPUT({
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
      await offlineInterfaceInfoUsingPUT({
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
   * @en-US International configuration
   * @zh-CN 国际化配置
   * */

  const columns: ProColumns<API.InterfaceInfoVo>[] = [
    {
      title: 'ID',
      dataIndex: 'id',
      valueType: "index",
    },
    {
      title: '接口名称',
      dataIndex: 'name',
      valueType: "text",
      formItemProps: {
        rules: [{
          required: true
        }]
      }
    },
    {
      title: '描述',
      dataIndex: 'description',
      valueType: 'textarea',
      formItemProps: {
        rules: [{
          required: true
        }]
      }
    },
    {
      title: '接口类型',
      dataIndex: 'method',
      valueType: 'text',
      formItemProps: {
        rules: [{
          required: true
        }]
      }
    },
    {
      title: '接口地址',
      dataIndex: 'host',
      valueType: 'text',
      formItemProps: {
        rules: [{
          required: true
        }]
      }
    },
    {
      title: '接口路径',
      dataIndex: 'uri',
      valueType: 'text',
      formItemProps: {
        rules: [{
          required: true
        }]
      }
    },
    {
      title: '请求参数',
      dataIndex: 'requestParams',
      valueType: 'jsonCode',
      formItemProps: {
        rules: [{
          required: true
        }]
      }
    },
    {
      title: '请求头',
      dataIndex: 'requestHeader',
      valueType: 'jsonCode',
      formItemProps: {
        rules: [{
          required: true
        }]
      }
    },
    {
      title: '响应头',
      dataIndex: 'responseHeader',
      valueType: 'jsonCode',
      formItemProps: {
        rules: [{
          required: true
        }]
      }
    },
    {
      title: '状态',
      dataIndex: 'status',
      hideInForm: true,
      valueEnum: {
        0: {
          text: '关闭',
          status: 'Default',
        },
        1: {
          text: '开启',
          status: 'Processing',
        },
      },
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      valueType: 'dateTime',
      hideInForm: true,
    },
    {
      title: '操作',
      dataIndex: 'option',
      valueType: 'option',
      render: (_, record) => [
        <a
          key="config"
          onClick={() => {
            handleUpdateModalOpen(true);
            setCurrentRow(record);
          }}
        >
          修改
        </a>,
        record.status === 0 ? <a
          key="config"
          onClick={() => {
            handleOnline(record);
          }}
        >
          发布
        </a> : null,
        record.status === 1 ? <a
          key="config"
          onClick={() => {
            handleOffline(record);
          }}
        >
          下线
        </a> : null,
        <Button
          type="text"
          key="config"
          danger
          onClick={() => {
            handleRemove([record]);
          }}
        >
          删除
        </Button>,
      ],
    },
  ];
  // @ts-ignore
  // @ts-ignore
  // @ts-ignore
  return (
    <PageContainer>
      <ProTable<API.InterfaceInfoVo, API.PageParams>
        headerTitle={'接口管理'}
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
            <PlusOutlined/> 新建
          </Button>,
        ]}
        request={getData}
        columns={columns}
        rowSelection={{
          onChange: (_, selectedRows) => {
            setSelectedRows(selectedRows);
          },
        }}
      />
      {selectedRowsState?.length > 0 && (
        <FooterToolbar
          extra={
            <div>
              已选择{' '}
              <a
                style={{
                  fontWeight: 600,
                }}
              >
                {selectedRowsState.length}
              </a>{' '}
              项 &nbsp;&nbsp;
              <span>
                服务调用次数总计 {selectedRowsState.reduce((pre, item) => pre , 0)} 万
              </span>
            </div>
          }
        >
          <Button
            onClick={async () => {
              await handleRemove(selectedRowsState);
              setSelectedRows([]);
              actionRef.current?.reloadAndRest?.();
            }}
          >
            批量删除
          </Button>
          <Button type="primary">批量审批</Button>
        </FooterToolbar>
      )}

      <UpdateModal
        columns={columns}
        onSubmit={async (value) => {
          const success = await handleUpdate(value);
          if (success) {
            handleUpdateModalOpen(false);
            setCurrentRow(undefined);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
        onCancel={() => {
          handleUpdateModalOpen(false);
          if (!showDetail) {
            setCurrentRow(undefined);
          }
        }}
        visible={updateModalOpen}
        //@ts-ignore
        values={currentRow || {}}
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
      <CreateModal
        columns={columns}
        onCancel={() => {
          handleModalOpen(false)
        }}
        onSubmit={(values) => {
          handleAdd(values)
        }}
        visible={createModalOpen}/>
    </PageContainer>
  );
};
export default InterfaceInfo;
