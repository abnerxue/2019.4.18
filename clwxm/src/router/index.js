import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '@/components/HomePage'
import Charts from "@/components/Charts"
import BicycleStatistics from "@/components/numericalStatement/BicycleStatistics"
import MotorcadeStatistics from "@/components/numericalStatement/MotorcadeStatistics"
import MapDistribution from "@/components/MapDistribution"
import Login from "@/components/Login"
import CarManagement from "@/components/enterpriseConstruction/CarManagement"
import VehicleMonitoring from "@/components/operationRegulation/VehicleMonitoring"
import AlarmMonitor from "@/components/operationRegulation/AlarmMonitor"
import PersonalEntry from "@/components/enterpriseConstruction/PersonalEntry"
import AlarmDetail from "@/components/alarmManagement/AlarmDetail"
import ChargeStatistics from "@/components/numericalStatement/ChargeStatistics"
import BicycleAction from "@/components/operationRegulation/BicycleAction"
import weixinManagement from "@/components/enterpriseConstruction/weixinManagement"
import vehicleVedio from "@/components/operationRegulation/VehicleVedio"
import RoleManagement from "@/components/userManagement/RoleManagement"
import UserManagement from "@/components/userManagement/UserManagement"
import RealTimeMonitoring from "@/components/operationRegulation/realTimeMonitoring";
import CarDetail from "@/components/operationRegulation/carDetail/CarDetail";
import CarItemDetail from "@/components/operationRegulation/carDetail/CarItemDetail";
import CarVedio from "@/components/operationRegulation/carDetail/CarVedio";
import DriveLine from "@/components/operationRegulation/carDetail/DriveLine";
import VehicleTracking from "@/components/operationRegulation/carDetail/VehicleTracking";

Vue.use(Router)

const router = new Router({
  mode: 'hash',
  routes: [{
      path: '/homePage',
      name: 'HomePage',
      component: HomePage,
      children: [{
          path: 'test/echarts',
          component: Charts,
        },
        {
          path: 'vehicleStatistics/bicycleStatistics',
          component: BicycleStatistics,
        },
        {
          path: 'vehicleStatistics/motorcadeStatistics',
          component: MotorcadeStatistics,
        },
        {
          path: 'vehicleStatistics/chargeStatistics',
          component: ChargeStatistics,
        },
        {
          path: 'controlCenter/mapDistribution',
          component: MapDistribution,
        },
        {
          path: 'enterpriseConstruction/carManagement',
          component: CarManagement,
        },
        {
          path: 'enterpriseConstruction/weixinManagement',
          component: weixinManagement,
        },
        {
          path: 'operationRegulation/vehicleMonitoring',
          component: VehicleMonitoring,
        },
        {
          path: 'operationRegulation/realTimeMonitoring',
          component: RealTimeMonitoring,
        },

        {
          path: 'enterpriseConstruction/personalEntry',
          component: PersonalEntry,
        },
        {
          path: 'operationRegulation/alarmMonitor',
          component: AlarmMonitor,
        },
        {
          path: 'operationRegulation/VehicleVedio',
          component: vehicleVedio,
        },
        {
          path: 'operationRegulation/bicycleAction',
          component: BicycleAction,
        },
        {
          path: 'operationRegulation/carDetail',
          component: CarDetail,
          children: [{
              path: 'carItemDetail',
              component: CarItemDetail,
            },
            {
              path: 'driveLine',
              component: DriveLine,
            },
            {
              path: 'carVedio',
              component: CarVedio,
            },
            {
              path: 'vehicleTracking',
              component: VehicleTracking,
            },

          ]
        },
        {
          path: 'alarmManagement/alarmDetail',
          component: AlarmDetail,
        },
        {
          path: 'userManagement/roleManagement',
          component: RoleManagement,
        },
        {
          path: 'userManagement/userManagement',
          component: UserManagement,
        }
      ]
    },
    {
      path: '/',
      name: 'Login',
      component: Login
    }

  ]
})

export default router;