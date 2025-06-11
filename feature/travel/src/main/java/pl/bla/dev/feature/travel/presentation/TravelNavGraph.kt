package pl.bla.dev.feature.travel.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import pl.bla.dev.common.core.navigation.AppNavController
import pl.bla.dev.common.core.navigation.Destination
import pl.bla.dev.common.core.navigation.DestinationType
import pl.bla.dev.common.core.navigation.createDestination
import pl.bla.dev.common.core.viewmodel.ContractViewModel
import pl.bla.dev.common.ui.componenst.dialog.DialogData
import pl.bla.dev.feature.travel.presentation.screen.dialog.TravelDialogScreen
import pl.bla.dev.feature.travel.presentation.screen.dialog.TravelDialogVM
import pl.bla.dev.feature.travel.presentation.screen.dialog.TravelDialogVMImpl
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.NewTravelDateScreen
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.NewTravelDateVM
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldate.NewTravelDateVMImpl
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.NewTravelDestinationScreen
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.NewTravelDestinationVM
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtraveldestination.NewTravelDestinationVMImpl
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.NewTravelOriginScreen
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.NewTravelOriginVM
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelorigin.NewTravelOriginVMImpl
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.NewTravelVehicleScreen
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.NewTravelVehicleVM
import pl.bla.dev.feature.travel.presentation.screen.newtravel.newtravelvehicle.NewTravelVehicleVMImpl

fun NavGraphBuilder.travelNavGraph(
  appContractVM: ContractViewModel,
  navController: AppNavController,
  onResult: (TravelResults) -> Unit,
) {
  navigation(
    route = TravelDestinations.TravelGraph.route,
    startDestination = appContractVM.retrieveData<Destination>(
      destination = TravelDestinations.TravelGraph,
    )?.route ?: TravelDestinations.NewTravelVehicle.route,
  ) {
    createDestination<Nothing, TravelContractVM, NewTravelVehicleVMImpl, NewTravelVehicleVM.Action.Navigation>(
      destination = TravelDestinations.NewTravelVehicle,
      navController = navController,
      content = { viewModel ->
        NewTravelVehicleScreen(viewModel = viewModel)
      },
      navActionHandler = { action, contractViewModel ->
        when (action) {
          is NewTravelVehicleVM.Action.Navigation.Back -> onResult(TravelResults.Close)
          is NewTravelVehicleVM.Action.Navigation.ShowDialog -> {
            contractViewModel.setContractData(
              destination = TravelDestinations.TravelDialog,
              data = action.dialogData,
            )
            navController.navigate(TravelDestinations.TravelDialog)
          }
          is NewTravelVehicleVM.Action.Navigation.ToOrigin -> {
            contractViewModel.setContractData(
              destination = TravelDestinations.NewTravelOrigin,
              data = action.setupData,
            )
            navController.navigate(TravelDestinations.NewTravelOrigin)
          }
        }
      },
    )

    createDestination<NewTravelOriginVM.NewTravelSetupData, TravelContractVM, NewTravelOriginVMImpl, NewTravelOriginVM.Action.Navigation>(
      destination = TravelDestinations.NewTravelOrigin,
      navController = navController,
      content = { viewModel ->
        NewTravelOriginScreen(viewModel = viewModel)
      },
      navActionHandler = { action, contractViewModel ->
        when (action) {
          is NewTravelOriginVM.Action.Navigation.Back -> navController.popBackStack()
          is NewTravelOriginVM.Action.Navigation.ShowDialog -> {
            contractViewModel.setContractData(
              destination = TravelDestinations.TravelDialog,
              data = action.dialogData,
            )
            navController.navigate(TravelDestinations.TravelDialog)
          }
          NewTravelOriginVM.Action.Navigation.CloseProcess -> onResult(TravelResults.Close)
          is NewTravelOriginVM.Action.Navigation.ToDestination -> {
            contractViewModel.setContractData(
              destination = TravelDestinations.NewTravelDestination,
              data = action.setupData,
            )
            navController.navigate(TravelDestinations.NewTravelDestination)
          }
        }
      },
    )

    createDestination<NewTravelDestinationVM.NewTravelSetupData, TravelContractVM, NewTravelDestinationVMImpl, NewTravelDestinationVM.Action.Navigation>(
      destination = TravelDestinations.NewTravelDestination,
      navController = navController,
      content = { viewModel ->
        NewTravelDestinationScreen(viewModel = viewModel)
      },
      navActionHandler = { action, contractViewModel ->
        when (action) {
          is NewTravelDestinationVM.Action.Navigation.Back -> navController.popBackStack()
          is NewTravelDestinationVM.Action.Navigation.ShowDialog -> {
            contractViewModel.setContractData(
              destination = TravelDestinations.TravelDialog,
              data = action.dialogData,
            )
            navController.navigate(TravelDestinations.TravelDialog)
          }
          NewTravelDestinationVM.Action.Navigation.CloseProcess -> onResult(TravelResults.Close)
          is NewTravelDestinationVM.Action.Navigation.ToDate -> {
            contractViewModel.setContractData(
              destination = TravelDestinations.NewTravelDate,
              data = action.setupData,
            )
            navController.navigate(TravelDestinations.NewTravelDate)
          }
        }
      },
    )

    createDestination<NewTravelDateVM.NewTravelSetupData, TravelContractVM, NewTravelDateVMImpl, NewTravelDateVM.Action.Navigation>(
      destination = TravelDestinations.NewTravelDate,
      navController = navController,
      content = { viewModel ->
        NewTravelDateScreen(viewModel = viewModel)
      },
      navActionHandler = { action, contractViewModel ->
        when (action) {
          is NewTravelDateVM.Action.Navigation.Back -> navController.popBackStack()
          is NewTravelDateVM.Action.Navigation.ShowDialog -> {
            contractViewModel.setContractData(
              destination = TravelDestinations.TravelDialog,
              data = action.dialogData,
            )
            navController.navigate(TravelDestinations.TravelDialog)
          }
          NewTravelDateVM.Action.Navigation.CloseProcess -> onResult(TravelResults.Close)
        }
      },
    )

    createDestination<DialogData, TravelContractVM, TravelDialogVMImpl, TravelDialogVM.Action.Navigation>(
      destination = TravelDestinations.TravelDialog,
      destinationType = DestinationType.Dialog,
      navController = navController,
      content = { viewModel ->
        TravelDialogScreen(viewModel = viewModel)
      },
      navActionHandler = { action, _ ->
        when (action) {
          is TravelDialogVM.Action.Navigation.OnDialogAction -> {
            navController.popBackStack()
            action.dailogAction()
          }
        }
      }
    )
  }
}