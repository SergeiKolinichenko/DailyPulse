//
//  SourcesScreen.swift
//  iosApp
//
//  Created by Serj on 30.10.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

extension SourcesListView {
    
    @MainActor
    class SourcesViewModelWrapper: ObservableObject {
        let sourcesViewModel: SourcesViewModel
        
        init() {
            sourcesViewModel = SourcesInjector().sourcesViewModel
            sourcesState = sourcesViewModel.sourcesState.value
        }
        
        @Published var sourcesState: SourcesState
        
        func startObserving() {
                    Task {
                        for await  sourcesS in sourcesViewModel.sourcesState {
                            self.sourcesState = sourcesS
                        }
                    }
                }
    }
    
}

struct SourcesScreen: View {
    @Environment(\.dismiss)
    private var dismiss
    
    let sourcesListScreen = SourcesListView(viewModel: .init())
    
    var body: some View {
        NavigationStack{
            sourcesListScreen
                .navigationTitle("Sources")
                .toolbar{
                    ToolbarItem(placement: .primaryAction){
                        Button {
                            dismiss()
                        } label: {
                            Text("Done").bold()
                        }
                    }
                
                }
        }
    }
}

struct SourcesListView: View {
    @ObservedObject private(set) var viewModel: SourcesViewModelWrapper
    
    var body: some View {
        if(viewModel.sourcesState.loading) {
            Loader()
        }
        if let error = viewModel.sourcesState.error{
            ErrorMessage(message: error)
        }
        if(!viewModel.sourcesState.sources.isEmpty){
            ScrollView{
                LazyVStack(spacing: 10){
                    ForEach(viewModel.sourcesState.sources, id: \.self) { source in
                        SourceItemView(source: source)
                    }
                }
            }
        }
    }
    
}

struct SourceItemView: View {
    var source: Source
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(source.name)
                .font(.title)
                .fontWeight(.bold)
            Text(source.desc)
            Text(source.origin)
        }.padding(16)
    }
}

#Preview {
    SourcesScreen()
}
